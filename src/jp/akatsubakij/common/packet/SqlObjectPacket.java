package jp.akatsubakij.common.packet;

import jp.akatsubakij.config.*;

import java.util.Collections;
import java.util.List;

/**
 * SqlObjectPacket
 */
public class SqlObjectPacket extends CommonPacket{
  private String mQuery;
  private List<String> mQueryResult;

  public SqlObjectPacket() {
    super();
    this.mQuery = CommonConstants.mNone;
    this.mQueryResult = Collections.emptyList();
  }

  /**
   * @return the mQuery
   */
  public String getmQuery() {
    return mQuery;
  }

  /**
   * @param mQuery the mQuery to set
   */
  public void setmQuery(String mQuery) {
    this.mQuery = mQuery;
  }

  /**
   * @return the mQueryResult
   */
  public List<String> getmQueryResult() {
    return mQueryResult;
  }

  /**
   * @param mQueryResult the mQueryResult to set
   */
  public void setmQueryResult(List<String> mQueryResult) {
    this.mQueryResult = mQueryResult;
  }
}