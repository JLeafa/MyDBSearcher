package jp.akatsubakij.common.packet;

import java.io.Serializable;

/**
 * CommonPacket
 */
public class CommonPacket implements Serializable{
  private Integer mUserUniqueId;
  private Integer mDestinationId;
  private String mMessageType;

  public CommonPacket() {
    this.mUserUniqueId = -1;
    this.mDestinationId = -1;
    this.mMessageType = "None";
  }

  /**
   * @return the mUserUniqueId
   */
  public Integer getmUserUniqueId() {
    return mUserUniqueId;
  }
  
  /**
   * @param mUserUniqueId the mUserUniqueId to set
   */
  public void setmUserUniqueId(Integer mUserUniqueId) {
    this.mUserUniqueId = mUserUniqueId;
  }

  /**
   * @return the mDestinationId
   */
  public Integer getmDestinationId() {
    return mDestinationId;
  }

  /**
   * @param mDestinationId the mDestinationId to set
   */
  public void setmDestinationId(Integer mDestinationId) {
    this.mDestinationId = mDestinationId;
  }

  /**
   * @return the mMessageType
   */
  public String getmMessageType() {
    return mMessageType;
  }
  
  /**
   * @param mMessageType the mMessageType to set
   */
  public void setmMessageType(String mMessageType) {
    this.mMessageType = mMessageType;
  }
}