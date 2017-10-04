package server.io.dao;

import org.apache.log4j.Logger;
import server.io.model.TitleEntity;
import util.Trace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TitleTable {
	private Logger logger = Trace.getInstance().getLogger("operation_file");
	List<TitleEntity> titleList=new ArrayList<TitleEntity>();
    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    private TitleTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
    	String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
    	for(int i=0;i<ISBNList.length;i++){
			TitleEntity detitle=new TitleEntity(ISBNList[i],titlenameList[i]);
    		titleList.add(detitle);
		}
    	logger.info(String.format("Operation:Initialize TitleTable;TitleTable: %s", titleList));
    };
    public static final TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }

    public Object createTitle(String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			TitleEntity newtitle=new TitleEntity(string,string2);
			result=titleList.add(newtitle);
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Success", string,string2));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Fail;Reason:The ISBN already existed.", string,string2));
		}
		return result;	
	}

	public Optional<TitleEntity> lookup(Predicate<TitleEntity> predicate) {
		return titleList
			.stream()
			.filter(predicate)
			.findFirst();
	}

	public TitleEntity remove(String isbn) {
		Optional<TitleEntity> title = titleList
				.stream()
				.filter(userEntity -> userEntity.getISBN().equals(isbn))
				.findFirst();

		if (!title.isPresent()) {
			return null;
		}

		titleList.remove(title.get());
		return title.get();
	}

	public List<TitleEntity> getTitleTable() {
		return titleList;
	}
}
