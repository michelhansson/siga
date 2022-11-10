package br.jus.trf2.assijus;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.crivano.blucservice.api.IBlueCrystal;
import com.crivano.swaggerservlet.SwaggerCall;
import com.crivano.swaggerservlet.SwaggerUtils;

import br.jus.trf2.assijus.IAssijus.ISignedAttrsPost;
import br.jus.trf2.assijus.IAssijus.SignedAttrsPostRequest;
import br.jus.trf2.assijus.IAssijus.SignedAttrsPostResponse;

public class SignedAttrsPost implements ISignedAttrsPost {

	@Override
	public void run(SignedAttrsPostRequest req, SignedAttrsPostResponse resp) throws Exception {
		String authkey = req.authkey;
		String cpf = Utils.assertValidAuthKey(authkey, Utils.getUrlBluCServer()).cpf;
		buildSignedAttrs(req, resp);
	}

	public void buildSignedAttrs(SignedAttrsPostRequest req, SignedAttrsPostResponse resp) throws Exception {
		String time = SwaggerUtils.format(new Date());
		String policy = req.policy;
		if (policy == null && req.sha256 != null)
			policy = "AD-RB";

		IBlueCrystal.HashPostRequest q = new IBlueCrystal.HashPostRequest();
		q.certificate = req.certificate;
		q.time = SwaggerUtils.parse(time);
		q.policy = policy;
		q.sha1 = req.sha1;
		q.sha256 = req.sha256;
		q.crl = true;
		IBlueCrystal.HashPostResponse s = SwaggerCall
				.callAsync("bluc-hash", null, "POST", Utils.getUrlBluCServer() + "/hash", q,
						IBlueCrystal.HashPostResponse.class)
				.get(AssijusServlet.HASH_TIMEOUT, TimeUnit.SECONDS).getRespOrThrowException();
		resp.hash = s.hash;
		resp.policyversion = s.policyversion;
		resp.policy = s.policy;
		resp.time = SwaggerUtils.parse(time);
		resp.sha1 = req.sha1;
		resp.sha256 = req.sha256;
	}

	@Override
	public String getContext() {
		return "obter o pacote assinável";
	}

}
