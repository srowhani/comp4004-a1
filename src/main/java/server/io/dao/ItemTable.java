package main.java.server.io.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import main.java.server.io.model.ItemEntity;
import main.java.util.Trace;
import org.apache.log4j.Logger;

public class ItemTable {
	private Logger logger = Trace.getInstance().getLogger("operation_file");
	private List<ItemEntity> itemList = new ArrayList<>();
    private int numItems = 0;

	private static class ItemListHolder {
        private static final ItemTable INSTANCE = new ItemTable();
    }

    private ItemTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};
    	String[] cnList=new String[]{"1","1","1","1"};

    	for(int i=0; i < ISBNList.length; i++) {
    	    addItem(ISBNList[i]);
		}

    	logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));
    };

    public static final ItemTable getInstance() {
        return ItemListHolder.INSTANCE;
    }

    public ItemEntity addItem(String isbn) {
		if (!TitleTable.getInstance().lookup(title -> title.getISBN().equals(isbn)).isPresent()) {
			logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Fail;Reason:No such ISBN existed.", isbn, "N/A"));
			return null;
		}

		String copyNumber = String.valueOf(itemList
            .stream()
            .filter(item -> item.getISBN().equals(isbn))
            .count());

		ItemEntity itemEntity = new ItemEntity(numItems++, isbn, copyNumber);
        itemList.add(itemEntity);

        logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Success", isbn, copyNumber));

        return itemEntity;
	}
	public Optional<ItemEntity> lookup(Predicate<ItemEntity> itemEntityPredicate) {
        return itemList
            .stream()
            .filter(itemEntityPredicate)
            .findFirst();
	}
	public Object delete(String string, String string2) {
		//Since the itemid and copynumber in is automatically assigned to the item,upon its creation.
		//Each item has a unique itemid and copynumber.Even it is deleted,they can not be assigned to other item.
		//To maintain the correctness of the data,here instead delete index from the List.
		//I choose to remove the item's information instead the whole index.
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBN=(itemList.get(i)).getISBN();
			String copynumber=(itemList.get(i)).getCopyNumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				index=i;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			boolean loan=LoanTable.getInstance().checkLoan(string,string2);
			if(loan){
			itemList.get(index).setCopyNumber("N/A");
			result="success";
			logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Success", string,"N/A"));
			}else{
				result="Active Loan Exists";
				logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The item is currently on loan.", string,string2));
			}
		}else{
			result="The Item Does Not Exist";
			logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The Item Does Not Exist.", string,string2));
		}
		return result;
	}
	public void deleteAll(String string) {
		
	}

	public List<ItemEntity> getItemTable() {
		return itemList;
	}
}