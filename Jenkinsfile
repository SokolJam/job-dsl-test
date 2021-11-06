import groovy.io.FileType

String basePath = 'pipelines'



def pipelines = []
def dir = new File("${WORKSPACE}/jenkins/pipelines")
dir.eachFileRecurse (FileType.FILES) { file ->
    pipelines << file.name.split("\\.")[0]
}


for(pipeline in pipelines) {
    multibranchPipelineJob("$basePath/${pipeline}") {
        branchSources {
            branchSource {
                source {
                    github {
                        credentialsId('sokoljam_github')
                        repoOwner('SokolJam')
                        repository('job-dsl-test')
                        repositoryUrl('https://github.com/SokolJam/job-dsl-test.git')
                        configuredByUrl(false)
                        id("${pipeline}")
                    }
                }
                buildStrategies {
                    includeRegionBranchBuildStrategy {
                        includedRegions("jenkins/pipelines/${pipeline}.groovy")
                    }
                }
            }
        }
        
        configure {
            def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
            traits << 'org.jenkinsci.plugins.github__branch__source.BranchDiscoveryTrait' {strategyId(1)} <<
            'org.jenkinsci.plugins.github__branch__source.OriginPullRequestDiscoveryTrait' {strategyId(1)} <<
            'jenkins.plugins.git.traits.LocalBranchTrait' {localBranch()}
        }
            
        factory {
            workflowBranchProjectFactory {
                scriptPath("jenkins/pipelines/${pipeline}.groovy")
            }
        }
        
        
        triggers {
            periodic(1440)
        }
        
        orphanedItemStrategy {
            discardOldItems {
                getNumToKeep()
            }
        }
    }
}
