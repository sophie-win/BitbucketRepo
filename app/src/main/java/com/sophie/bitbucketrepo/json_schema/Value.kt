package com.sophie.bitbucketrepo.json_schema

import java.util.*

class Value {
    var scm: String? = null
    var website: String? = null
    var has_wiki = false
    var uuid: String? = null
    var links: Links? = null
    var fork_policy: String? = null
    var full_name: String? = null
    var name: String? = null
    var project: Project? = null
    var language: String? = null
    var created_on: Date? = null
    var mainbranch: Mainbranch? = null
    var workspace: Workspace? = null
    var has_issues = false
    var owner: Owner? = null
    var updated_on: Date? = null
    var size: Long = 0L
    var type: String? = null
    var slug: String? = null
    var is_private = false
    var description: String? = null
}