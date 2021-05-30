package com.sophie.bitbucketrepo.json_schema

class Links {
    var watchers: Watchers? = null
    var branches: Branches? = null
    var tags: Tags? = null
    var commits: Commits? = null
    var clone: List<Clone>? = null
    var self: Self? = null
    var source: Source? = null
    var html: Html? = null
    var avatar: Avatar? = null
    var hooks: Hooks? = null
    var forks: Forks? = null
    var downloads: Downloads? = null
    var pullrequests: Pullrequests? = null
    var issues: Issues? = null
}