#!/bin/bash

gradle clean installDist

./install/bin/WarO_Kotlin config.json

