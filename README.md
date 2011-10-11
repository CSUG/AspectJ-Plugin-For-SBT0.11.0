This is an AspectJ-SBT-Plugin I wrote for SBT0.11.0, but maybe it works on SBT0.10.x too(I didn't test it). 

You may wonder why I reinvent such a wheel, since there are alternatives for such things, e.g.  <a href="https://github.com/typesafehub/sbt-aspectj">Typesafehub's sbt-aspectj</a>. Yes, sbt-aspectj is ok for its job, but the weaving source and weaving target format are not what I want. I want do weaving with classes and aspect sources, not jars. That's why I wrote this.

## How to Use it?
It's simple, just put following lines into your $PROJECT_ROOT/project/plugins/build.sbt:
> resolvers += Resolver.url("githubResolver", url("git://github.com/fujohnwang/AspectJ-Plugin-For-SBT0.11.0.git"))
>
> addSbtPlugin("name.fujohnwang" % "aspectj\_sbt\_plugin" % "0.0.1") 

If you don't want to do other customizations, then its done.

Before running your main scala class(es), and if you want to weave in your aspects into the byte codes, run:
> aspect:weave

After that, your running result will reflect the weaved result(s).

## Are there any project structure changes?
Put your aspect source files into **"src/main/aspectj"**, nothing more.

## Can I customize the configuration or settings of the plugin?
Yes, currently, you can override the settings for **"aspectjVersion"** and **"aspectDirectory"**(override "_aspectjClasspath_" is not recommended).
Refer to SBT0.10+ document to see how to add more settings to your project.

