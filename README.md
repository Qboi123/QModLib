# QModLib
MC Modding Library for Qboi123's Mod(s).
   
## CurseForge
https://www.curseforge.com/minecraft/mc-mods/qmodlib  
  
## Mods that uses this library:
 * [QForgeMod](https://www.curseforge.com/minecraft/mc-mods/qforgemod)

## Using as API
1) Set environment variables for your OS. ([Windows](https://www.tenforums.com/tutorials/121855-edit-user-system-environment-variables-windows.html), [Linux](https://www.serverlab.ca/tutorials/linux/administration-linux/how-to-set-environment-variables-in-linux/), [Mac](https://medium.com/@himanshuagarwal1395/setting-up-environment-variables-in-macos-sierra-f5978369b255#:~:text=If%20the%20environment%20variable%20you,variable%20name%20and%20its%20value.))  
   Set `GITHUB_USERNAME` to your github username  
   Set `GITHUB_TOKEN` to your [token](https://github.com/settings/tokens).
2) Add the repository (`https://maven.pkg.github.com/Qboi123/QForgeMod`)
   ```gradle
   repositories {
       maven {
           name = "GitHubPackages"
           url = uri("https://maven.pkg.github.com/Qboi123/QForgeMod")
           credentials {
               username = System.getenv("GITHUB_USERNAME")
               password = System.getenv("GITHUB_TOKEN")
           }
       }
   }
   ```
3) Add the dependencies (`com.qsoftware:qforgemod`, `com.qsoftware:qmodlib`)
   ```
   dependencies {
       // Other dependencies here. //
       
       compileOnly fg.deobf("com.qsoftware:qmodlib")
       runtimeOnly fg.deobf("com.qsoftware:qmodlib")
       
       // Other dependencies here. //
   }
   ```
6) Reload gradle.
7) You're done!
