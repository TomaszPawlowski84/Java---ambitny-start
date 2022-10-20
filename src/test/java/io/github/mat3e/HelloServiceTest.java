package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private static final String WELCOME="Hello";
    private static final String FALLBACK_ID_WELCOME="Hola";

    @Test
    public void test_prepareGreeting_nulName_returnsGreetingWithFallbackName() throws Exception{
        //given
        var mocRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mocRepository);
        //when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(WELCOME +" "+ HelloService.FALLBACK_NAME + "!", result);
    }



    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        var SUT = new HelloService();
        //given
        var name = " test ";
        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() throws Exception{
        //given

        var mocRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mocRepository);
        //when
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_ID_WELCOME +" "+ HelloService.FALLBACK_NAME + "!", result);
    }
    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() throws Exception{
        //given
        var mocRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mocRepository);
        //when
        var result = SUT.prepareGreeting(null, "abc");
        //then
        assertEquals(FALLBACK_ID_WELCOME +" "+ HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "-1");
        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null,FALLBACK_ID_WELCOME,null));
                }
                return Optional.empty();
            }
        };
    }

    //metoda do tworzenia repozsytorium
    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null,WELCOME,null));
            }
        };
    }
}
