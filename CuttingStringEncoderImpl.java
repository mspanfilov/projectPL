import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

// как следует из задания, результирующий массив обратно собираться не должен, так как при разбиении строки часть данных теряется
public class CuttingStringEncoderImpl implements CuttingStringEncoder {

	@Override
	public List<String> cutAndEncode(String source, Charset charset,
			int maxSegLen) throws IllegalArgumentException {
		
                //List<byte[]> res = new ArrayList<byte[]>(); // результат
		  List<String> res = new ArrayList<String>(); // результат
		
			// проверка входящих параметров (не примитивов) на null
			if (source == null || charset == null) {
				throw new IllegalArgumentException("Есть пустые параметры!");
			} else {
				System.out.println("Начинаем резать и кодировать!");
				
				//кодируем исходную строку в нужную кодировку
				//byte[] encodesource = source.getBytes(/*charset*/);
                                //ByteBuffer bb = ByteBuffer.wrap(encodesource);
                                
                                //пока не кодируем, не переводим в байты и маскимальную длинну сегмента считаем в символах
				
                                //разбиваем исходную строку на массив слов
                                String[] sourcespl = source.split(" ");
                                
                                String tmp = "";                                // здесь сохраняем данные для формирования сегмента  
                                int totlength = 0;                              // общее количество символов в sourcespl
                                int totseghwoi = 0;                              // общее количество сегментов без индексов
                                int totlengthwi = 0;                            // общее количество символов с индексами
                                int y = 1;                                      // общее количество сегментов
                                // собираем результат из слов (без индекса) (результат в символах)
                                /*for (int i=0; i<sourcespl.length ;i++){
                                    if (!sourcespl[i].isEmpty()) {          // в результате сплита могут быть пустые слова - их не смотрим
                                
                                        System.out.println("tmp: " + tmp);       
                                        
                                        if (((tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i]).length() <= maxSegLen) {
                                            tmp = (tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i];
                                        }else{                              // если вылазим за границы сегмента 
                                            if (tmp.isEmpty()) {                // если это единственное слово в сегменте, 
                                                                                // то его разбиваем и пишем сегменты пока слово не кончится. а остаток передаем дальше
                                                for (int j=0; j<(sourcespl[i].length())/maxSegLen; j++){
                                                    res.add(sourcespl[i].substring(j*maxSegLen,j*maxSegLen+maxSegLen));
                                                }
                                                tmp = sourcespl[i].substring(sourcespl[i].length()-((sourcespl[i].length())%maxSegLen));
                                            }else{
                                                res.add(tmp);                   // записываем сегмент и откатываемся
                                                i--;
                                                tmp = "";
                                            }
                                        }
                                        
                                    }
                                }*/
                                
                                // собираем результат из слов (с индексом (Y пока константа)) (результат в символах)
                                /*for (int i=0; i<sourcespl.length ;i++){
                                    if (!sourcespl[i].isEmpty()) {          // в результате сплита могут быть пустые слова - их не смотрим
                                
                                        System.out.println("tmp: " + tmp);    
                                        //System.out.println("res.size(): " + res.size());  
                                        
                                        if (((tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i]).length() <= (maxSegLen-((res.size())/10)-4)) { //(/Y пока константа)
                                            tmp = (tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i];
                                            if (i == (sourcespl.length-1)) {// если  сегмент последний, то пишем
                                                res.add(tmp);
                                            }
                                        }else{                              // если вылазим за границы сегмента 
                                            if (tmp.isEmpty()) {                // если это единственное слово в сегменте, 
                                                                                // то его разбиваем и пишем сегменты пока слово не кончится. а остаток передаем дальше
                                            String lsource = "";                // здесь храним длиное слово с индексами    
                                            String index = "";                  // здесь храним индекс
                                            String end = "";                    // здесь храним хвост
                                                //if (res.size()>0) {                 // если сегмент уже не первый, то нужен индекс ПОЛУЧАЕТСЯ ИНДЕКС НУЖЕН ВСЕГДА
                                                    for (int k=0; k<(sourcespl[i].length()); k++){    // преобразуем длинное слово в lsource
                                                        if (k==0) {
                                                            lsource = res.size()+1 + "/Y ";
                                                        }
                                                        lsource = lsource + sourcespl[i].substring(k,k+1);
                                                        if ((lsource.length()%maxSegLen==0) && (k!=sourcespl[i].length()-1)) {
                                                            index = (res.size()+1 + (lsource.length()/maxSegLen)) + "/Y ";
                                                            lsource = lsource + index;
                                                        }
                                                    }
                                                    
                                                    System.out.println("lsource: " + lsource);
                                                            
                                                    for (int j=0; j<(lsource.length())/maxSegLen; j++){     // разбиваем и пишем сегменты пока lsource не останется только хвост
                                                        res.add(lsource.substring(j*maxSegLen,j*maxSegLen+maxSegLen));
                                                    }
                                                    System.out.println("что удалем из хвоста: " + index);
                                                    end = lsource.substring(lsource.length()-((lsource.length())%maxSegLen));
                                                    if ((i == (sourcespl.length-1)) && !(end.isEmpty())) {    // если  сегмент последний, то пишем хвост
                                                        res.add(end);
                                                    }else{                                                   // если  сегмент непоследний, то хвост запишем в tmp                                                 
                                                        tmp = end.replaceFirst(index, "");
                                                    }
                                                //}else{
                                                //    for (int j=0; j<(sourcespl[i].length())/maxSegLen; j++){
                                                //        res.add(sourcespl[i].substring(j*maxSegLen,j*maxSegLen+maxSegLen));
                                                //    }
                                                //    tmp = sourcespl[i].substring(sourcespl[i].length()-((sourcespl[i].length())%maxSegLen));
                                                //}
                                            }else{                              // записываем сегмент с индексом и откатываемся
                                                res.add(res.size()+1 + "/Y " + tmp);               
                                                i--;
                                                tmp = "";
                                            }
                                        }
                                        
                                    }
                                }*/
                                
                                // вычислим количество сегментов (Y)
                                /*for (int i=0; i < sourcespl.length; i++){                               // вычислим общее количество символов в sourcespl
                                    totlength = totlength + sourcespl[i].length();                  
                                }
                                totseghwoi = (totlength/maxSegLen) + ((totlength/maxSegLen)==0?0:1);    // вычислим общее количество сегментов без индексов
                                if (totseghwoi!=1){
                                    totlengthwi = totlength + totseghwoi*4 + totseghwoi;
                                }
                                
                                System.out.println("Y: " + Y);*/
                                
                                
                                // собираем результат из слов (результат в символах)
                                for (int i=0; i<sourcespl.length ;i++){
                                    if (!sourcespl[i].isEmpty()) {          // в результате сплита могут быть пустые слова - их не смотрим
                                
                                        System.out.println("tmp: " + tmp);    
                                        //System.out.println("res.size(): " + res.size());  
                                        
                                        if (((tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i]).length() <= (maxSegLen-((res.size())/10)-3- (((Integer)y).toString()).length() )) { //(/Y пока константа)
                                            tmp = (tmp.isEmpty()?tmp:(tmp + " ")) + sourcespl[i];
                                            if (i == (sourcespl.length-1)) {// если  сегмент последний, то пишем
                                                res.add(tmp); y++;
                                            }
                                        }else{                              // если вылазим за границы сегмента 
                                            if (tmp.isEmpty()) {                // если это единственное слово в сегменте, 
                                                                                // то его разбиваем и пишем сегменты пока слово не кончится. а остаток передаем дальше
                                            String lsource = "";                // здесь храним длиное слово с индексами    
                                            String index = "";                  // здесь храним индекс
                                            String end = "";                    // здесь храним хвост
                                                //if (res.size()>0) {                 // если сегмент уже не первый, то нужен индекс ПОЛУЧАЕТСЯ ИНДЕКС НУЖЕН ВСЕГДА
                                                    for (int k=0; k<(sourcespl[i].length()); k++){    // преобразуем длинное слово в lsource
                                                        if (k==0) {
                                                            lsource = res.size()+1 + "/" + ((Integer)y).toString() + " "; y++;
                                                        }
                                                        lsource = lsource + sourcespl[i].substring(k,k+1);
                                                        if ((lsource.length()%maxSegLen==0) && (k!=sourcespl[i].length()-1)) {
                                                            index = (res.size()+1 + (lsource.length()/maxSegLen)) + "/" + ((Integer)y).toString() + " ";
                                                            lsource = lsource + index;  y++;
                                                        }
                                                    }
                                                    
                                                    System.out.println("lsource: " + lsource);
                                                            
                                                    for (int j=0; j<(lsource.length())/maxSegLen; j++){     // разбиваем и пишем сегменты пока lsource не останется только хвост
                                                        res.add(lsource.substring(j*maxSegLen,j*maxSegLen+maxSegLen));
                                                    }
                                                    System.out.println("что удалем из хвоста: " + index);
                                                    end = lsource.substring(lsource.length()-((lsource.length())%maxSegLen));
                                                    if ((i == (sourcespl.length-1)) && !(end.isEmpty())) {    // если  сегмент последний, то пишем хвост
                                                        res.add(end); y++;
                                                    }else{                                                   // если  сегмент непоследний, то хвост запишем в tmp                                                 
                                                        tmp = end.replaceFirst(index, "");
                                                    }
                                                //}else{
                                                //    for (int j=0; j<(sourcespl[i].length())/maxSegLen; j++){
                                                //        res.add(sourcespl[i].substring(j*maxSegLen,j*maxSegLen+maxSegLen));
                                                //    }
                                                //    tmp = sourcespl[i].substring(sourcespl[i].length()-((sourcespl[i].length())%maxSegLen));
                                                //}
                                            }else{                              // записываем сегмент с индексом и откатываемся
                                                res.add(res.size()+1 + "/" + ((Integer)y).toString() + " " + tmp); y++;               
                                                i--;
                                                tmp = "";
                                            }
                                        }
                                        
                                    }
                                }
                                
                                
				//BufferedReader str = new BufferedReader(new StringReader(source));
                                
                                
				
                                /*byte[] tmp = new byte[3]; 
                                bb.get(tmp, 0, maxSegLen+1);
                                res.add(tmp);
                                
                                bb.get(tmp, 0, 0);
                                res.add(tmp);*/
                                
				//OutputStreamWriter osw = new OutputStreamWriter((OutputStream) res, charset);
				
				/*int ch;
				StringBuffer buffer = new StringBuffer();
				try {
					while ((ch = str.read())> -1){
						//res.add((byte)((char)ch));
						buffer.append((char)ch);
						System.out.println((char)ch);
						//osw.write(ch);
						//((char)ch);
						byte[] tmp = new byte[1];
						tmp[0] = (byte)ch;
						System.out.println(tmp[0]);
						//res.add(tmp);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(buffer);*/
				
				return res;
			}

	}

}
