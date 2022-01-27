rootProject.name = "MedicalTherapy"
include(":app")
include(":schedule")
include(":source")
include(":tools")

project(":schedule").projectDir = File(rootDir, "MedicalTherapy-Schedule")
project(":source").projectDir = File(rootDir, "MedicalTherapy-Source")
project(":tools").projectDir = File(rootDir, "WearTools")
