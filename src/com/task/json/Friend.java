package com.task.json;

/**
 * @author Leus Artem
 * @since 15.06.13
 */
public class Friend {

    private String id;
    private int priority;

    public Friend() {
    }

    public Friend(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Friend){
            Friend anotherFriend = (Friend) o;
            return anotherFriend.id.equals(id) && anotherFriend.priority == priority;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }
}
