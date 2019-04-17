# Interpreting-and-Checking-Tool
The EFSA’s Interpreting and checking tool (ICT), is a Microsoft Excel spreadsheet program which aim to translate and check the correctness of FoodEx2 codes. The tool is provided with different business rules and macros which allow to automatically interpret FoodEx2 codes.

The tool is an add-on of the Catalogue browser which, for this reason, should be installed in order to correctly use the ICT.

<p align="center">
    <img src="http://www.efsa.europa.eu/profiles/efsa/themes/responsive_efsa/logo.png" alt="European Food Safety Authority"/>
</p>

For being able to import the project into the Eclipse IDE it is needed first to have the following projects properly working into the workspace:
- EFSA_Catalogue_browser and all its dependencies (which contains mainly the business rules and all the methods able to read data from the databse),
- SQL statements executor (used for quering the database).

Please note that the source folder contains the **main**, the **catalogue** and the **catalogue_browser_dao** packages. The **main** package is used for launching the tools with its input variables while, the other two packages, are both overwriting the twin packages into the EFSA Catalogue browser project. The packages contain custom version of the Catalogue and Database manager classes which points to the ICT database and not the one used by the Catalogue browser in order to allow the user to launch both the tools at the same time.
