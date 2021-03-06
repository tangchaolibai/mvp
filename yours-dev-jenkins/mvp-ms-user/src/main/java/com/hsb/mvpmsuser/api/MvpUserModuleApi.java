/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.hsb.mvpmsuser.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsb.mvpmsuser.annotation.JwtIgnore;
import com.hsb.mvpmsuser.exception.MvpMsUserException;
import com.hsb.mvpmsuser.model.payload.ExceptionResponse;
import com.hsb.mvpmsuser.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsuser.model.payload.request.EditUserRequest;
import com.hsb.mvpmsuser.model.payload.request.LoginRequest;
import com.hsb.mvpmsuser.model.payload.request.RegisterRequest;
import com.hsb.mvpmsuser.model.payload.response.BaseResponse;
import com.hsb.mvpmsuser.model.payload.response.EditSystemSettingResponse;
import com.hsb.mvpmsuser.model.payload.response.EditUserResponse;
import com.hsb.mvpmsuser.model.payload.response.FindUserResponse;
import com.hsb.mvpmsuser.model.payload.response.GenVerificationCodeResponse;
import com.hsb.mvpmsuser.model.payload.response.LoginResponse;
import com.hsb.mvpmsuser.model.payload.response.RegisterResponse;
import com.hsb.mvpmsuser.model.payload.response.SearchUserResponse;
import com.hsb.mvpmsuser.model.payload.response.SendSMSResponse;
import com.hsb.mvpmsuser.model.payload.response.VerificationResultResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"MVP USER Module"})
public interface MvpUserModuleApi {

    @ApiOperation(value = "Login Yours System", nickname = "login", notes = "Login Yours System", response = LoginResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = LoginResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @PostMapping(value = "/login", produces = { "application/json" }, consumes = { "application/json" })
    ResponseEntity<LoginResponse> login(@ApiParam(value = "Login Request" ,required=true )  @Valid @RequestBody LoginRequest loginRequest) throws MvpMsUserException;

    
    @ApiOperation(value = "Register account for Yours System", nickname = "register", notes = "Register account for Yours System", response = RegisterResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = RegisterResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @PostMapping(value = "/register", produces = { "application/json" }, consumes = { "application/json" })
    ResponseEntity<RegisterResponse> register(@ApiParam(value = "Register Request" ,required=true )  @Valid @RequestBody RegisterRequest registerRequest) throws MvpMsUserException;
    
    
    @ApiOperation(value = "Edit user infomation", nickname = "editUser", notes = "Edit user infomation", response = EditUserResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = EditUserResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @PostMapping(value = "/user/edit/{userId}", produces = { "application/json" }, consumes = { "application/json" })
    ResponseEntity<EditUserResponse> editUser(@PathVariable("userId") String userId, @ApiParam(value = "Edit User Request" ,required=true )  @Valid @RequestBody EditUserRequest editUserRequest) throws MvpMsUserException;
    
    
    @ApiOperation(value = "Find user by Id", nickname = "findUser", notes = "Find user by Id", response = FindUserResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FindUserResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @GetMapping(value = "/user", produces = { "application/json" })
    ResponseEntity<FindUserResponse> findUser(@Valid @RequestParam(value = "userId", required = true) String userId) throws MvpMsUserException;
    
    
    @ApiOperation(value = "Log off user by Id", nickname = "logOffUser", notes = "Log off user by Id", response = BaseResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BaseResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @PostMapping(value = "/user/close/{userId}", produces = { "application/json" })
    ResponseEntity<BaseResponse> closeAccount(@PathVariable("userId") String userId) throws MvpMsUserException;
    
    
    @ApiOperation(value = "Log out user by Id", nickname = "logOutUser", notes = "Log out user by Id", response = BaseResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BaseResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @PostMapping(value = "/user/logout/{userId}", produces = { "application/json" })
    ResponseEntity<BaseResponse> logOutUser(@PathVariable("userId") String userId) throws MvpMsUserException;
    
    
    @ApiOperation(value = "System Setting of Edit System Setting", nickname = "editSystemSetting", notes = "System Setting of Edit System Setting", response = BaseResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BaseResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @PostMapping(value = "/setting/editSystemSetting", produces = { "application/json" })
    ResponseEntity<EditSystemSettingResponse> editSystemSetting(@ApiParam(value = "Edit System Setting Request" ,required=true )  @Valid @RequestBody EditSystemSettingRequest editSystemSettingRequest);
    
    
    @ApiOperation(value = "Get System Setting", nickname = "getSystemSetting", notes = "Get System Setting", response = EditSystemSettingResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = EditSystemSettingResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @GetMapping(value = "/setting/getSystemSetting", produces = { "application/json" })
    ResponseEntity<EditSystemSettingResponse> getSystemSetting( @ApiParam(value = "The userId of User", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId);
    
    
    @ApiOperation(value = "get user list", nickname = "getUserList", notes = "get user list", response = SearchUserResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SearchUserResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @GetMapping(value = "/user/getUserList", produces = { "application/json" })
    ResponseEntity<SearchUserResponse> getUserList(@NotNull @Valid @RequestParam("assistantMsg")String assistantMsg);
    
    
    @JwtIgnore
    @ApiOperation(value = "get verification code", nickname = "getVerificationCode", notes = "get verification code", response = GenVerificationCodeResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = GenVerificationCodeResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @GetMapping(value = "/user/generateOtp", produces = { "application/json" })
    ResponseEntity<GenVerificationCodeResponse> getVerificationCode(@NotNull @Valid @RequestParam("mobile phone")String phoneNumber);
    
    
    @JwtIgnore
    @ApiOperation(value = "verification", nickname = "getVerificationResult", notes = "verification", response = VerificationResultResponse.class, tags={ "MVP USER Module", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VerificationResultResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class) })
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="header",name="Authorization",dataType="String",required=false,value="token",defaultValue="AAA.BBB.CCC"),
    })
    @GetMapping(value = "/user/verifyOtp", produces = { "application/json" })
    ResponseEntity<VerificationResultResponse> getVerificationResult(@NotNull @Valid @RequestParam("mobile phone")String phoneNumber,
    		@NotNull @Valid @RequestParam("verification code")String verificationCode);
    
    
    @ApiOperation(value = "send SMS For Invitation", nickname = "sendSMSForInvitation", notes = "send SMS For Invitation", response = SendSMSResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SendSMSResponse.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name="Authorization", dataType = "String", required = true, value = "token", defaultValue="Bearer "),
    })
    @PostMapping(value = "/user/invite/sendSMS", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SendSMSResponse> sendSMS(@ApiIgnore @RequestHeader("Authorization") String token, @NotNull @RequestParam("phoneNumber")String phoneNumber) throws MvpMsUserException;
    
}
