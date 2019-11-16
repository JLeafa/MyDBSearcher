package jp.akatsubakij.common.packet;

/**
 * HelloPacket
 */
public class HelloPacket extends CommonPacket{
  private String mMessage;

  public HelloPacket(){
    super();
    this.mMessage = "";
  }

  /**
   * @return the mMessage
   */
  public String getmMessage() {
    return mMessage;
  }

  /**
   * @param mMessage the mMessage to set
   */
  public void setmMessage(String mMessage) {
    this.mMessage = mMessage;
  }
}