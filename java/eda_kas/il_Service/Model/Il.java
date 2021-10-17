package eda_kas.il_Service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
//data yazınca set ve get e veriler ekleniyor
@NoArgsConstructor //mesela boş constructor ve id,isiml, constructor yazmamıza gerke yok
@AllArgsConstructor //bu sayede daha temiz bir kod yazmış oluyoruz
public class Il {
    private Date createDate;
    private  String id;
    private String name;


}
