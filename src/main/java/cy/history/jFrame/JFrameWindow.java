package cy.history.jFrame;


import java.util.Arrays;
import java.util.List;

public class JFrameWindow  { //需要继承JFrame


    public static void main(String[] args) {

        int y =22;

        List<Integer> list  = Arrays.asList(1,23,4,5,6,7);


        list.forEach((itm) -> {
            itm=y;
        });
        BedChangeDTO dto = new BedChangeDTO();
        dto.setCdBedB("dddd");
        List<BedChangeDTO> list2  = Arrays.asList(dto,dto);
        String ss="ffff";
        list2.forEach(itm ->{
            itm.setCdBedB(ss);
        });
        list2.forEach(itm ->{
            System.out.println(itm.getCdBedB());
        });


    }
}
