#!/bin/sh
GRADLE_APP_NAME="Gradle"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-${HOME}/.gradle}"
GRADLE_OPTS="${GRADLE_OPTS:--Xmx64m -Xms64m}"

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`
APP_HOME=`pwd -P`

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

exec java $GRADLE_OPTS -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"