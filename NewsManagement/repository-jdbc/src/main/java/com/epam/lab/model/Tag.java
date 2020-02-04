package com.epam.lab.model;

public class Tag extends AbstractEntity {
    private long tagId;
    private String tagName;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagId != tag.tagId) return false;
        return tagName != null ? tagName.equals(tag.tagName) : tag.tagName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("tagId=").append(tagId);
        sb.append(", tagName='").append(tagName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
