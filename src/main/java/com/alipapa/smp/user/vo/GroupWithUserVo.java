package com.alipapa.smp.user.vo;

import java.util.List;

public class GroupWithUserVo {

    private Long groupId;

    private String groupNo;

    private String groupName;

    private Long leaderId;

    private String leaderName;

    private List<FuzzyUserVo> members; //组员列表


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FuzzyUserVo> getMembers() {
        return members;
    }

    public void setMembers(List<FuzzyUserVo> members) {
        this.members = members;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
