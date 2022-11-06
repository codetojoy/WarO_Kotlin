#!/bin/bash

./gradlew clean installDist

./install/bin/WarO_Kotlin config.json

