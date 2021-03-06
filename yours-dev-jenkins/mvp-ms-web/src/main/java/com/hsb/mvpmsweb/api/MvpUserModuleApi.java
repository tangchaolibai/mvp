/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.hsb.mvpmsweb.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.hsb.mvpmsweb.api.annotation.JwtIgnore;
import com.hsb.mvpmsweb.api.annotation.NeedToken;
import com.hsb.mvpmsweb.api.annotation.ResponseConfig;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.constant.HttpStatusCodeConstants;
import com.hsb.mvpmsweb.constant.Swagger2Constants;
import com.hsb.mvpmsweb.model.payload.request.CancelUserRequest;
import com.hsb.mvpmsweb.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsweb.model.payload.request.EditUserRequest;
import com.hsb.mvpmsweb.model.payload.request.ForgetPasswordRequest;
import com.hsb.mvpmsweb.model.payload.request.LoginRequest;
import com.hsb.mvpmsweb.model.payload.request.RegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.UserIdRequest;
import com.hsb.mvpmsweb.model.payload.request.ValidateRegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.VerifyOtpRequest;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.response.EditSystemSettingResponse;
import com.hsb.mvpmsweb.model.payload.response.EditUserResponse;
import com.hsb.mvpmsweb.model.payload.response.FindUserResponse;
import com.hsb.mvpmsweb.model.payload.response.LoginResponse;
import com.hsb.mvpmsweb.model.payload.response.OTPResponse;
import com.hsb.mvpmsweb.model.payload.response.RegisterResponse;
import com.hsb.mvpmsweb.model.payload.response.SearchUserResponse;
import com.hsb.mvpmsweb.model.payload.response.SendSMSResponse;
import com.hsb.mvpmsweb.model.payload.response.ValidateRegisterResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = Swagger2Constants.TAG_NM_USER, description = Swagger2Constants.TAG_DESC_USER)
@ApiSupport(order = 40, author = "zhaoyue@formssi.com")
public interface MvpUserModuleApi {

	@JwtIgnore
    @ApiOperation(value = "Login Yours System", nickname = "login", notes = "Login Yours System", response = LoginResponse.class)
    @ApiOperationSupport(order = 20)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = LoginResponse.class))
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LoginResponse> login(@ApiParam(value = "Login Request", required = true) @Valid @RequestBody LoginRequest loginRequest) throws MvpUserException;

    
	@JwtIgnore
    @ApiOperation(value = "Register account for Yours System", nickname = "register", notes = "Register account for Yours System", response = RegisterResponse.class)
    @ApiOperationSupport(order = 10)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = RegisterResponse.class))
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegisterResponse> register(@ApiParam(value = "Register Request", required = true) @Valid @RequestBody RegisterRequest registerRequest) throws MvpUserException;
    
    
    @ApiOperation(value = "Edit user info", nickname = "editUser", notes = "Edit user info", response = EditUserResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = EditUserResponse.class))
    @ApiOperationSupport(order = 40)
    @PostMapping(value = "/user/editUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EditUserResponse> editUser(@ApiParam(value = "Edit User Request", required = true) @Valid @RequestBody EditUserRequest editUserRequest) throws MvpUserException;
    
    
    @ApiOperation(value = "Find user by Id", nickname = "findUser", notes = "Find user by Id", response = FindUserResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = FindUserResponse.class))
    @ApiOperationSupport(order = 30)
    @GetMapping(value = "/user/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FindUserResponse> findUser(@Valid @RequestParam(value = "userId", required = true) String userId) throws MvpUserException;
    
    
    @ApiOperation(value = "Cancel User", nickname = "cancelUser", notes = "Cancel User", response = BaseResponse.class)
    @NeedToken
    @ApiOperationSupport(order = 50)
    @ResponseConfig
    @PostMapping(value = "/user/cancelUser", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BaseResponse> cancelUser(@ApiParam(value = "Cancel User Request", required = true) @Valid @RequestBody CancelUserRequest cancelUserRequest) throws MvpUserException;
    
    
    @ApiOperation(value = "Log out user by Id", nickname = "logOutUser", notes = "Log out user by Id", response = BaseResponse.class)
    @NeedToken
    @ApiOperationSupport(order = 60)
    @ResponseConfig
    @PostMapping(value = "/user/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BaseResponse> logOutUser(@ApiParam(value = "Cancel User Request", required = true) @RequestBody UserIdRequest request) throws MvpUserException;
    
    
    @ApiOperation(value = "System Setting of Edit System Setting", nickname = "editSystemSetting", notes = "System Setting of Edit System Setting", response = BaseResponse.class)
    @NeedToken
    @ResponseConfig
    @ApiOperationSupport(order = 70)
    @PostMapping(value = "/setting/editSystemSetting", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EditSystemSettingResponse> editSystemSetting(@ApiParam(value = "Edit System Setting Request", required = true) @Valid @RequestBody EditSystemSettingRequest editSystemSettingRequest);
    
    
    @ApiOperation(value = "Get System Setting", nickname = "getSystemSetting", notes = "Get System Setting", response = EditSystemSettingResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = EditSystemSettingResponse.class))
    @NeedToken
    @ApiOperationSupport(order = 80)
    @GetMapping(value = "/setting/getSystemSetting", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EditSystemSettingResponse> getSystemSetting(@ApiParam(value = "The userId of User", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId) throws MvpUserException;
    
    
    @ApiOperation(value = "get user list", nickname = "getUserList", notes = "get user list", response = SearchUserResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = SearchUserResponse.class))
    @NeedToken
    @ApiOperationSupport(order = 90)
    @GetMapping(value = "/user/getUserList", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SearchUserResponse> getUserList(@NotNull @Valid @RequestParam("assistantMsg")String assistantMsg);
    
    
    @JwtIgnore
    @ApiOperation(value = "Generate Otp", response = OTPResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = OTPResponse.class))
    @ApiOperationSupport(order = 100)
    @GetMapping(value = "/user/generateOtp", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OTPResponse> generateOtp(@ApiParam(value = "Phone Number", required = true) @Valid @RequestParam(value = "phoneNumber", required = true) String phoneNumber);
    
    
    @JwtIgnore
    @ApiOperation(value = "Verify Otp", notes = "Verify Otp", response = OTPResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = OTPResponse.class))
    @ApiOperationSupport(order = 110)
    @PostMapping(value = "/user/verifyOtp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OTPResponse> verifyOtp(@ApiParam(value = "Verify Otp Request", required = true)@Valid @RequestBody(required = true) VerifyOtpRequest verifyOtpRequest);
    
    
    @ApiOperation(value = "send SMS For Invitation", nickname = "sendSMSForInvitation", notes = "send SMS For Invitation", response = SendSMSResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = SendSMSResponse.class))
    @NeedToken
    @ApiOperationSupport(order = 120, author = "mojianheng@formssi.com")
    @PostMapping(value = "/user/invite/sendSMS", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SendSMSResponse> sendSMS(@ApiIgnore @RequestHeader("Authorization") String token, @NotNull @RequestParam("phoneNumber")String phoneNumber) throws MvpUserException;

    
    @JwtIgnore
    @ApiOperation(value = "Validate Register", response = ValidateRegisterResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = ValidateRegisterResponse.class))
    @ApiOperationSupport(order = 130, author = "mojianheng@formssi.com")
    @PostMapping(value = "/user/mobilePhone/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ValidateRegisterResponse> validateRegister(@ApiParam(value = "Validate Register Request", required = true) @Valid @RequestBody ValidateRegisterRequest request) throws MvpUserException;
    
    @JwtIgnore
    @ApiOperation(value = "Forget Password", response = BaseResponse.class)
    @ApiOperationSupport(order = 130)
    @PostMapping(value = "/user/forgetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BaseResponse> forgetPassword(@ApiParam(value = "Forget Password Request", required = true) @Valid @RequestBody ForgetPasswordRequest request) throws MvpUserException;
    
}
