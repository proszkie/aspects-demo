package com.proszkie.aspectsdemo.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class LogTimeAspect {

    @Pointcut("@annotation(timeMeasurement) && execution(* *.*(..))")
    void timeMeasurement(TimeMeasurement timeMeasurement){

    }

    @Around("timeMeasurement(timeMeasurement)")
    Object measureTime(final ProceedingJoinPoint pjp, final TimeMeasurement timeMeasurement) throws Throwable {
        final String logTemplate = timeMeasurement.value();
        final int[] argIndexes = timeMeasurement.argIndexes();
        final Method[] methods = timeMeasurement.methods();

        final StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        final Object result = pjp.proceed();

        stopwatch.stop();

        final Object[] arguments = Stream.concat(Stream.of(stopwatch.getTotalTimeMillis()), getArguments(pjp.getArgs(), argIndexes, methods)).toArray();

        log.info(logTemplate, arguments);

        return result;
    }

    private Stream<Object> getArguments(final Object[] args, final int[] argIndexes, final Method[] methods) {
        return Arrays.stream(argIndexes)
                .mapToObj(index -> new MethodInvocation(args[index], methods[index]))
                .map(MethodInvocation::getResult);
    }

    @AllArgsConstructor
    private static class MethodInvocation {
        private final Object arg;
        private final Method method;

        Object getResult(){
            return method.invoke(arg);
        }
    }
}
