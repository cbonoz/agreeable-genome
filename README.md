
<div style="text-align:center">
    <img src="./app/src/main/res/drawable/genome_logo_green.png" style="margin: 0 auto"/>
</div>

Agreeable Genome
---

Agreeable Genome is a daily recipe app that recommends daily meals and health tips that complement
        your personal genome. Your genome/health info has automatically been pulled in.

Created for the GENOME LINK API Devpost challenge.

### What's the Problem

‘All standardized diets specify what you should eat based upon the average human metabolism, but as we know from the science of the individual, there is no such thing as average metabolism, any more than there is an average body, average brain, or average genome. Instead, the way each of our bodies processes food is completely individualized--and demands a completely individualized diet’ (1)

Still somewhat early in the lifetime for genome-based web and mobile apps - sequencing the full human genome is still cost
prohibitive and/or not of proven interest to folks, so it may be some time before such applications have mainstream application.

Agreeable Genome is a venture into this domain, by seeking to create a platform where people can learn about their human genome,
while simulatenously be recommended smarter food choices and health tips which are communicated and tracked within the mobile
application.

###

For more information, see the Pitch Deck:
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



