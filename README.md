
<img src="./app/src/main/res/drawable/genome_logo_trans.png" style="margin: 0 auto"/>

Agreeable Genome
---

Agreeable Genome is a daily recipe app that recommends daily meals and health tips that complement
        your personal genome. Your genome/health info has automatically been pulled in.

### What's the Problem


###

See Pitch Deck:

https://docs.google.com/presentation/d/16EUqJsdWubQcpF0FHXg9JK_0yrnByLmPfOG61lP34mI/edit?usp=sharing

### Screenshots


### Next Work

* Set up scheduled push notifications for recipes.
* Add additional health & wellness features.
* Set up paid features such as advanced research, sponsored meal plans specifically tailored to your Genome, and additional
tips/recipes.

### Dev Notes

* To set up, will need to modify the following block in your build.gradle with API credentials from
<a href="https://www.edamam.com/">https://www.edamam.com/</a>
<pre>
debug {
            buildConfigField 'String', "EdamamApiKey", Edamam_ApiKey
            buildConfigField 'String', "EdamamAppId", Edamam_AppId
            resValue 'string', "api_key", Edamam_ApiKey
            resValue 'string', "api_id", Edamam_AppId
        }
</pre>

* Min Android API: 19



