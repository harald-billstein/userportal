package billstein.harald.userportal.api;

/**
 * Holds a key value pair.
 * 
 * @author Harald & Stefan
 * @since 2017-12-14
 *
 */
public class RequestPropertyPair {

  private String key;
  private String value;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
