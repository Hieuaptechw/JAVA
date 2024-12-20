import java.io.Serializable;

public class Member implements Serializable {
    private String memberId;
    private String memberName;
    private String address;

    public Member() {
    }

    public Member(String memberId, String memberName, String address) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    public String convertData(String separator) {
        StringBuilder sb = new StringBuilder();
        return sb.append(memberId)
                .append(separator)
                .append(memberName)
                .append(separator)
                .append(address)
                .toString();
    }
}
