import ProjectVersions.rlVersion

/*
 * Copyright (c) 2019 Owain van Brakel <https://github.com/Owain94>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

description = "Slayer"
version = "0.0.1"

val deps = configurations.create("deps")

dependencies {
    annotationProcessor(Libraries.lombok)
    annotationProcessor(Libraries.pf4j)

    compileOnly("com.openosrs:runelite-api:$rlVersion")
    compileOnly("com.openosrs:runelite-client:$rlVersion")
    compileOnly("com.openosrs:http-api:$rlVersion")

    compileOnly(project(":xptracker"))

    compileOnly(Libraries.guice)
    compileOnly(Libraries.gson)
    compileOnly(Libraries.javax)
    compileOnly(Libraries.jopt)
    compileOnly(Libraries.lombok)
    compileOnly(Libraries.pf4j)

    testImplementation("com.openosrs:runelite-api:$rlVersion")
    testImplementation("com.openosrs:runelite-client:$rlVersion")
    testImplementation("com.openosrs:http-api:$rlVersion")

    testImplementation(Libraries.pf4j)
    testImplementation(Libraries.guiceTestlib)
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockitoCore)
    testImplementation(Libraries.mockitoInline)
}

tasks {
    jar {
        manifest {
            attributes(mapOf(
                    "Plugin-Version" to project.version,
                    "Plugin-Id" to nameToId(project.name),
                    "Plugin-Class" to "net.runelite.client.plugins.slayer.SlayerPluginWrapper",
                    "Plugin-Provider" to "OpenOSRS",
                    "Plugin-Dependencies" to nameToId("xptracker"),
                    "Plugin-Description" to "Utilizes the GPU",
                    "Plugin-License" to "3-Clause BSD License"
            ))
        }

        from(deps.map { if (it.isDirectory) it else zipTree(it) })
    }
}