<p align="center">
	<img src="http://www.efsa.europa.eu/profiles/efsa/themes/responsive_efsa/logo.png" alt="European Food Safety Authority"/>
</p>

# Interpreting and checking tool
The EFSA Interpreting and checking tool (ICT), is a Microsoft Excel spreadsheet program which aim to translate and check the correctness of FoodEx2 codes.
The project is provided with different business rules (writtend in an external java file) and macros which allow to automatically interpret FoodEx2 codes.
The ICT is an add-on of the Catalogue browser and, for this reason, it should be installed in order to correctly use the ICT.

## Dependencies
All project dependencies are listed in the [pom.xml](pom.xml) file.

## Import the project
In order to import the project correctly into the integrated development environment (e.g. Eclipse), it is necessary to download the project together with all its dependencies.
The project and all its dependencies are based on the concept of "project object model" and hence Apache Maven is used for the specific purpose.
In order to correctly import the project into the IDE it is firstly required to create a parent POM Maven project (check the following [link](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) for further information). 
Once the parent project has been created add the project and all the dependencies as "modules" into the pom.xml file as shown below: 

	<modules>

		<!-- dependency modules -->
		<module>module_1</module>
		...
		...
		...
		<module>module_n</module>
		
	</modules>
	
Next, close the IDE and extract all the zip packets inside the parent project.
At this stage you can simply open the IDE and import back the parent project which will automatically import also the project and all its dependencies.

_Please note that the "SWT.jar" and the "Jface.jar" libraries (if used) must be downloaded and installed manually in the Maven local repository since are custom versions used in the tool ((install 3rd party jars)[https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html])._

_Please note that the source folder contains the **main**, the **catalogue** and the **catalogue_browser_dao** packages. The **main** package is used for launching the tools with its input variables while, the other two packages, are both overwriting the twin packages into the EFSA Catalogue browser project. The packages contain custom version of the Catalogue and Database manager classes which points to the ICT database and not the one used by the Catalogue browser in order to allow the user to launch both the tools at the same time._
