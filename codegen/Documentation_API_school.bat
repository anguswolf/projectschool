RMDIR /Q/S ProjectSchool

rem https://openapi-generator.tech/docs/generators/spring/

java -Xmx1024M -jar openapi-generator-cli-5.3.0.jar ^
   generate -i "C:\Users\ThinkOpen\Desktop\esercizi_java\projectschool\codegen\documentation_API_school.yaml" ^
   -c "ProjectSchool.json" ^
   -g spring  ^
   -o "ProjectSchool"


set IFSRC=ProjectSchool\src\main\java\com\exercise\projectschool\generated\api
set IFDST=..\src\main\java\com\exercise\projectschool\generated\api
copy %IFSRC%\*api.java %IFDST%
copy %IFSRC%\apiutil.java %IFDST%

copy ProjectSchool\src\main\java\com\exercise\projectschool\generated\model\*.java ^
..\src\main\java\com\exercise\projectschool\generated\model


RMDIR /Q/S ProjectSchool


