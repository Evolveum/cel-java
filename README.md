# Evolveum Fork of cel-java

This project contains fork of [cel-java project](https://github.com/cel-expr/cel-java/), a Java implementation of Common Expression Language.

## How Is This Fork Different?

This fork brings **nullability** to the CEL world.
The code of the CEL engine is modified to work seamlessly with null values in CEL expressions.
E.g., following CEL expression fails miserably in original CEL implementation, in case there are null values in the "focus" data structure.

```cel
focus.activation.administrativeStatus == 'enabled'
```

This fork brings null-tolerant implementation of CEL.
The expression above works well when interpreted by the code in this fork, with any combination of null values.
Expression authors do not need to care about null values most of the time, and they can focus on the business aspects of the expression, as they are supposed to.

## Purpose, Security and Quality

This code is maintained solely for the use in [midPoint](https://github.com/Evolveum/midpoint).
Tests in this fork are not maintained, as we were not able to make the test work. 
Honestly, we do not think it is work the effort to fight a serious battle with Bazel just to be able to run the tests.
The code in this fork in tested indirectly, using tests in midPoint.
However, these tests are testing only the scenarios that midPoint is using.

Therefore, there are **no guarantees** that this code will work without midPoint, and absolutely no guarantees regarding security of the code when used outside midPoint.

Use at your own risk.

## Why Fork?

Why are we forking and not contributing back to the original project instead?

Simply speaking, mostly due to the Google CLA.
We are not willing to sign it, which is showstopper for any contribution.

However, there seems to be also deeper incompatibilities of this fork with the approach of original team, which makes the contribution unlikely to be accepted even if we did sign the CLA.

Overall, CEL seems to suffer from serious nullophobia (irrational fear of null values).
This fork is fixing that.

To be fair, CEL introduced "optionals" to make working with null values possible.
However, using optionals is not easy, especially for people who are not expert programmers.
This would not work for us.
Null-tolerant language is much better way that optionals for our use cases.

Also, we have to make a serious butchery of the basel-based build configuration, to be able to build the project at all, without dependency on Google infrastructure.

## Build

Use `build.sh` to build the project (Bazel is required).

Use `publish/publish.sh` to publish Maven artifacts.
