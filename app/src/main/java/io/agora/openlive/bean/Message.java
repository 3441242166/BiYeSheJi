package io.agora.openlive.bean;

import androidx.annotation.Nullable;

public class Message implements Comparable<Message> {

    public static final String STATE_DOING = "处理中";
    public static final String STATE_UN_DOING = "未处理";
    public static final String STATE_FINISH = "已结束";

    public String address;
    public String time;
    public String state;
    public String notice;
    public boolean isTop;

    public Message(String address, String time, String state, String notice) {
        this.address = address;
        this.time = time;
        this.state = state;
        this.notice = notice;
        isTop = false;
    }

    @Override
    public int compareTo(Message bean) {
        if (isTop && !bean.isTop) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Message) {
            Message msg = (Message) obj;
            return msg.address.equals(address) && msg.notice.equals(notice)
                    && msg.time.equals(time);
        }
        return false;
    }
}
