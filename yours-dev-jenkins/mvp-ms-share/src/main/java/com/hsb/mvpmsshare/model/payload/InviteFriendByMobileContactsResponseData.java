package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * InviteFriendByMobileContactsResponseData
 */
@Validated

public class InviteFriendByMobileContactsResponseData   {
  @JsonProperty("message")
  private String message = null;

  public InviteFriendByMobileContactsResponseData message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Invite Friend By Mobile Contacts Response Data
   * @return message
  **/
  @ApiModelProperty(value = "Invite Friend By Mobile Contacts Response Data")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InviteFriendByMobileContactsResponseData inviteFriendByMobileContactsResponseData = (InviteFriendByMobileContactsResponseData) o;
    return Objects.equals(this.message, inviteFriendByMobileContactsResponseData.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InviteFriendByMobileContactsResponseData {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

