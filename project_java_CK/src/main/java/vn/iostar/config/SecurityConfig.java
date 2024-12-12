package vn.iostar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
//@EnableWebSecurity
public class SecurityConfig  {
//    private final String[] PUBLIC_API = {
//            "/home_user","/users"
//    };
//    @NonFinal
//    protected static final String SIGN_KEY =
//            "llOLLAGjN8hjLQTCefRomDKTGzzBxZISXiNKTgt9LNai5o1gHSCkFr9uR5cmrNFJ";
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(
//                request -> request.requestMatchers(HttpMethod.GET, PUBLIC_API)
//                        .anonymous().requestMatchers(HttpMethod.POST, PUBLIC_API)
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//
//        );
//        httpSecurity.oauth2ResourceServer(
//                oauth2 -> oauth2.jwt(
//                        jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
//        );
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        return httpSecurity.build();
//    }
//    @Bean
//    JwtDecoder jwtDecoder(){
//        SecretKeySpec spec = new SecretKeySpec(SIGN_KEY.getBytes() ,"HS512");
//        return NimbusJwtDecoder.withSecretKey(spec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }
        @Bean
        public PasswordEncoder passwordEncoder (){
            return new BCryptPasswordEncoder(10 );
        }
}