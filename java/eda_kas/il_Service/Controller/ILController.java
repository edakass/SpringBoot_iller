package eda_kas.il_Service.Controller;

import eda_kas.il_Service.Model.Il;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/iller")
//request mapping hangi url de çalışacağını giriyoruz,browser üzerinden çekebiliyoruz
//sadece request mapping kullanmak yetmiyor,birde get metodu yazmamız lazım

public class ILController {
    //restcontrollerde bilgi almak için genelde get metodları kullanılır
    //bilgi göndermek için,eklemek için post kullanılır

    private static final List<Il> iller=new ArrayList<>();
    /*
    spring boot'a ne zaman request gönderirsek bizim için yeni bir object oluşturuyor
    controller'lardan bir new yapıyor
     */

   /* private final List<Il> iller;

    public  ILController(List<Il> iller){
        this.iller = iller;
        Il il1=new Il("60","Tokat");
        Il il2=new Il("05","Amasya");

        iller= Arrays.asList(il1,il2);
    }*/
    public  ILController(){
        if (iller.isEmpty()){
            Il il1=new Il(new Date(),"60","Tokat");
            Il il2=new Il(new Date(),"05","Amasya");

            iller.add(il1);
            iller.add(il2);
            // iller= Arrays.asList(il1,il2);
        }
    }

    @GetMapping
    public ResponseEntity<List<Il>> getIller(){
         return new ResponseEntity<>(iller, OK);
         //HttpStatus hata verirse .Not_Found ya da Internal_Server_Error eklemek zorundasınız
         //OK'u static import yaptık
         /*ResponseEntity-genelde rest ortamında kullanılan bir class'lardır.
          bunu şu şekilde de yapılabilirdi üsteki kod
          
           public ResponseEntity<List<Il>> getIller(){
           Il il1=new Il("60","Tokat");
           Il il2=new Il("05","Amasya");

           List<Il> iller= Arrays.asList(il1,il2);

           return new ResponseEntity<>(iller, OK);

          @GetMapping
          @ResponseStatus(HttpStatus.ACCEPTED) ACCEPTED gibi herhangi
           public List<Il> getIller(){
              Il il1=new Il("60","Tokat");
              Il il2=new Il("05","Amasya");

             return new  Arrays.asList(il1,il2);
           bu şekilde de çalışır.
             Arasındaki fark,yukardaki https i siz belirliyorsunuz
             Bu en son yaptığımızda ise genellikle spring boot https status 200 geri veriyor
             bunu sonradan biz değiştiremiyoruz
          */
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Il> getIl(@PathVariable String id){
        Il result= iller.stream()
                .filter(il->il.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new RuntimeException("IL not found"));
        return new ResponseEntity<>(result,OK);


        /*GetMapping deki id'yi kodda çekmek için @PathVariable kavramı var
        String id ve getmappind de de id olduğu için otomatikmen ekliyor

         For ile yapmak isteseydik
          Il result=null;

          for(int i=0;  i<iller.size(); i++){
             Il il=iller.get(i);
             if(il.getId().equals(id)){
               result il;
              }
            }
           if(result==null){
              throw new RuntimeException("Il not found");
             }
         */


        //STREAM yerine for da kullanılabilir
        /* streamApi ı kullanıyoruz,bir stream açıyoruz ve diyoruz ki
          bunun içinde bana bir filtre yap,filtre nasıl olsun herhangi bir ilin il id si verdiğim id ye
          yani(String id'ye metoddaki) eşit ise ve ilk bulduğunu çık buradan ve buraya(result'a) ekle.
          Bu olmaz ise bir hatayı at
        */

    }
    @PostMapping
    /*PostMapping de
    2 tane ilimiz var üçüncüyüde eklemek istiyoruz bunun için kullanıyıruz.
    ResponseEntity<Void> oluşturuyorum ama geriyehiçbir şey vermiyorum demek
    ResponseEntity<String> oluşturduğumuz objectin id sini verebiliriz
     ResponseEntity<Il> oluşturduğumuz objenin kendisini de verebiliriz
     */
    public  ResponseEntity<Il> createIl(@RequestBody Il newIl){ //gelen object in il classından olduğunu belirtiyoruz
        newIl.setCreateDate(new Date());
        iller.add(newIl);
        return  new ResponseEntity<>(newIl, HttpStatus.CREATED);
        /*Hostlara genelde created veriliyor,numarası 201 dir.
        HttpStatus'un 200 numaralı olanları http de okey anlamına gelir.
         */
    }
}
