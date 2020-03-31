package _04_order.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {
	
	private Map<Integer, OrderItemBean> cart = new LinkedHashMap<>();
	
	public ShoppingCart() {
		
	}

	public Map<Integer, OrderItemBean> getContent(){
		return cart;
	}
						//還需要確認我們商品的ID是啥
	public void addToCart(int productId, OrderItemBean oib) {
		if(oib.getQuantity() <=0) {
			return;
		}
		
		//如果購物車裡沒有此商品 加進購物車的map
		if(cart.get(productId) == null){
			
			cart.put(productId,oib);
		}else {
			//如果有的話 取得在購物車裡該商品的ID
			OrderItemBean oiBean = cart.get(productId);
			//並把原有的數量加進來
			oiBean.setQuantity(oib.getQuantity() + oiBean.getQuantity());
		}
	}
		//如果用js寫 應該不用  更動數量
	public boolean changeQty(int productId, int newQty) {
		if(cart.get(productId) != null) {
			OrderItemBean bean = cart.get(productId);
			bean.setQuantity(newQty);
			return true;
		}else {
			return false;
		}
	}
	
	//刪除購物車內的商品
	public int deleteProduct(int productId) {
		if(cart.get(productId) != null) {
			cart.remove(productId);
			return 1;
		}else {
			return 0;
		}
	}
	
	public int getItemNumber() {
		return cart.size();
	}
	
	//計算加總 可能也不會用到 會使用js
	public int getSubTotal() {
		Integer subTotal = 0;
		Set<Integer> set = cart.keySet();
		for(int n : set) {
			OrderItemBean oib = cart.get(n);
			Integer price = oib.getUnitPrice();
			Integer quantity = oib.getQuantity();
			subTotal += price * quantity; 
		}
		return subTotal;
	}
	
	public int addProductId(int productId) {
		return productId;
	}
	
	
}

