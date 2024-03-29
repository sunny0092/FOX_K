package com.ihaha.sunny.fox.data.mappers

import com.ihaha.sunny.fox.local.entity.user.UserPostItem as DbUserPostItem
import com.ihaha.sunny.fox.remote.models.user.UserPostItem

internal fun UserPostItem.toDbUserPostItem() : DbUserPostItem{
    return DbUserPostItem(
        id = this.id,
        answersCount = this.answersCount,
        following = this.following,
        questionsCount = this.questionsCount,
        followersCount = this.followersCount,
        name = this.name,
        postsCount = this.postsCount,
        username = this.username,
        reputation = this.reputation,
        bannedAt = this.bannedAt,
        avatar = this.avatar
    )
}