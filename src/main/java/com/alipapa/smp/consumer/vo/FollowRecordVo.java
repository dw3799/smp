package com.alipapa.smp.consumer.vo;

public class FollowRecordVo {

    private Long followRecordId;

    private Long consumerId;

    private String followTime;

    private String followUserName;


    private String followUserNo;

/*    private String name;

    private String userNo;*/

    private String content;


    public Long getFollowRecordId() {
        return followRecordId;
    }

    public void setFollowRecordId(Long followRecordId) {
        this.followRecordId = followRecordId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName;
    }

    public String getFollowUserNo() {
        return followUserNo;
    }

    public void setFollowUserNo(String followUserNo) {
        this.followUserNo = followUserNo;
    }
}
