pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HealthHub"
include(
    ":app",
    ":data:account",
    ":data:appointments",
    ":data:authentication",
    ":data:home",
    ":data:info",
    ":data:medicalfile",
    ":data:util",
    ":domain:account",
    ":domain:provider",
    ":feature:account",
    ":feature:appointments",
    ":feature:authentication",
    ":feature:home",
    ":feature:info",
    ":feature:medicalfile",
    ":network",
    ":ui:catalog",
    ":ui:navigation",
)