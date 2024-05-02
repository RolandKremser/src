package All_Unlimited.Allgemein;

//import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
//import java.util.Properties;
import java.util.Set;

import com.microsoft.aad.msal4j.IAccount;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.InteractiveRequestParameters;
import com.microsoft.aad.msal4j.PublicClientApplication;
//import com.microsoft.aad.msal4j.UserNamePasswordParameters;

public class Azure {
	String sAppId;
	List<String> appScopes;
	String sTenantId;
	String sRedirectUrl;


	public boolean init(Global g)
	{
//		g.fixInfo("Azure-Init");
		Parameter Para=new Parameter(g,"Azure");
        Para.getMParameterH("Server");
//        String sDom;
//        String sID;
//        String sTenant=null;
//        List<String> sScope=null;
        if (Para.bGefunden)
        {
          String s = Para.sParameterzeile;
          String sAry[]=s.split("&");
//          sServer=sAry[0];
//          sDom=sAry[1];
          sAppId=sAry[2];
//          g.fixInfo("AppId="+sAppId);
          sTenantId=sAry[3];
//          g.fixInfo("TenantId=="+sTenantId);
          sRedirectUrl = sAry[0]; //"http://localhost";
//          g.fixInfo("RedirectUrl="+sRedirectUrl);
          if (sAry.length>4)
        	  appScopes=Arrays.asList(sAry[4].split(","));
          return sAry.length>4;
        }
        else
        {
          g.printError("Azure.init: Parameter noch nicht definiert");
          return false;
        }
	}
	
//	public String aquireToken(Global g) {
//		return "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyJ9.eyJhdWQiOiI5ODIwMjIxOS01YWZkLTQ1NjgtODQ2Yi1jN2M4YjgyZGZhMmYiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vODliYTc3ZTUtMTBiNi00YmEzLTlmNGUtOTAyMWRmMDMyNzA4L3YyLjAiLCJpYXQiOjE2NTM4OTE2NTUsIm5iZiI6MTY1Mzg5MTY1NSwiZXhwIjoxNjUzODk1NTU1LCJhaW8iOiJBVFFBeS84VEFBQUEvY3FhSEpTSm95bTdTU090ZDkxSEN5Nk5uQVpLNHRDRmREcWcySGRrb05LS0U4ZElPWThkdXlOeFRFc1lFVFRwIiwibmFtZSI6IkFsbHVubGltaXRlZCBEZW1vIiwib2lkIjoiOWY3MzYzODYtYTQ5NC00YmE0LWE2MTUtYjdjNjQyNTQ1MTM0IiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWxsZGVtb0BtYXJla3dvcmtzLmF0IiwicmgiOiIwLkFSQUE1WGU2aWJZUW8wdWZUcEFoM3dNbkNCa2lJSmo5V21oRmhHdkh5TGd0LWk4UUFKWS4iLCJzdWIiOiJLRFVIV1l4V3dFU3d2TmpoX2Zzb3FDSVVUUTZPbVd1X0dRd2FUQTZ5cms4IiwidGlkIjoiODliYTc3ZTUtMTBiNi00YmEzLTlmNGUtOTAyMWRmMDMyNzA4IiwidXRpIjoiRGoybmcyT2EwMEdhUFhIaWJPd2FBQSIsInZlciI6IjIuMCJ9.qGWfpqVCg6BgEbjeJMHHyl3yYGOyWQVy5p0GRgD-mT25s7gYHHI_MX4VHLtyX3ZgtRcpq8atpD1W1L685X5YXc3y1GXBh6bElRhVqWvmQqWNLCLbiYT6sVm6WUQvOV224AwenQ9Ady9Whp9pxcl3ZCGrItsIuSfBS4NoctXKp5l8vldU_GLFFKSe8Pz-dgOQRS435KvuGofuKV_tGjJRs7r7C8ZElXdT4R2MU4aQq_fOwABismvWcHmuqgu3e3xIm8fWWzgH15At9SgjyGIEgsZny9SXmrtoP1kNFW3MUXZP0g_Lf5nEr-PZGgCE_fpaFDHOvMQnQD4HlEMZyXPXTw\r\n"; 
//			
//	}
//	
//	public String getUserName()
//	{
//		return "alldemo@marekworks.at";
//	}
	
	IAuthenticationResult lastResponse = null;
	PublicClientApplication app;
	public String aquireToken(Global g) {
		try {
			app = PublicClientApplication.builder(sAppId) 
					.authority("https://login.microsoftonline.com/" + sTenantId)
					.build();

//			Set<IAccount> accounts = app.getAccounts().get();
//			Set<IAccount> accounts = app.getAccounts().join();
//			System.out.println("Accounts:"+accounts.size());
//			if (accounts.size() != 1) 
			{
				// Authorize

				Set<String> scopeSet = new HashSet<String>();
				scopeSet.addAll(appScopes);
//				String signedClientAssertion = ComputeAssertion();
				InteractiveRequestParameters params = InteractiveRequestParameters
						.builder(URI.create(sRedirectUrl))
						.scopes(scopeSet)
//						.withClientAssertion("ALL_UNLIMITED")
						.build(); 
//				String clientSecret="15982bff-3603-4cd9-bf56-e6f4390776b5";
//				UserNamePasswordParameters params2 =
//	                    UserNamePasswordParameters
//	                    .builder(scopeSet, sAppId, clientSecret.toCharArray())
//	                    .build();
				IAuthenticationResult response = app.acquireToken(params).get();
//				g.fixtestError("Account="+response.account().homeAccountId()+"/"+response.account().username()+"/"+response.account().getTenantProfiles());
//				app.acquireTokenSilently(params)
//				System.out.println("Gütig bis="+response.expiresOnDate());
//				System.out.println("scopes="+response.scopes());
				lastResponse = response;
				String sIdToken=response.idToken();
//				g.fixtestError("ID_token="+sIdToken);
				String sAccessToken=response.accessToken();
//				g.fixtestError("Access_token="+sAccessToken);
				return sIdToken==null ? sAccessToken:sIdToken; 
			}
		} catch (Exception e) {
			g.printStackTrace(e);
		}
		
		return null;
	}
	
	public String getUserName() {
		if (lastResponse == null) return null;
		IAccount account = lastResponse.account();
		if (account == null) return null;
	
		return account.username();
	}
	
	public String logout() {
		if (lastResponse == null) return "bereits ausgeloggt";
		IAccount account = lastResponse.account();
		app.removeAccount(account);
		return "neu ausgeloggt";
	}

}