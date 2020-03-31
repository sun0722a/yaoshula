package _04_order.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import _05_product.model.ProductFormatBean;

public class ShoppingCart {

	private Map<Integer, Map<OrderItemBean, Set<ProductFormatBean>>> cart = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	public Map<Integer, Map<OrderItemBean, Set<ProductFormatBean>>> getContent() {
		return cart;
	}

	// 加入購物車(Map)
	public void addToCart(int productFormatId, OrderItemBean oib, Set<ProductFormatBean> formats) {
		if (oib.getQuantity() <= 0) {
			return;
		}
		// 如果購物車裡沒有此規格商品 => 加進購物車的map
		if (cart.get(productFormatId) == null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = new LinkedHashMap<OrderItemBean, Set<ProductFormatBean>>();
			orderMap.put(oib, formats);
			cart.put(productFormatId, orderMap);
		} else {
			// 如果有的話 取得在購物車裡該商品的ID
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatId);
			OrderItemBean oiBean = orderMap.keySet().iterator().next();
			// 並把原有的數量加進來
			oiBean.setQuantity(oib.getQuantity() + oiBean.getQuantity());
		}
	}

	// 更動購物車內的商品數量
	public boolean changeQty(int productFormatId, int newQty) {
		if (cart.get(productFormatId) != null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatId);
			OrderItemBean bean = orderMap.keySet().iterator().next();
			bean.setQuantity(newQty);
			return true;
		} else {
			return false;
		}
	}

	// 更動購物車內的商品規格
	public boolean changeFormat(int productFormatId, String content1, String content2) {
		if (cart.get(productFormatId) != null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatId);
			OrderItemBean bean = orderMap.keySet().iterator().next();
			if (content1 != null) {
				bean.setFormatContent1(content1);
			}
			if (content2 != null) {
				bean.setFormatContent2(content2);
			}
			return true;
		} else {
			return false;
		}
	}

	// 刪除購物車內的商品
	public int deleteProduct(int productFormatId) {
		if (cart.get(productFormatId) != null) {
			cart.remove(productFormatId);
			return 1;
		} else {
			return 0;
		}
	}

	public int getItemNumber() {
		return cart.size();
	}

	// 計算購物車內的商品價格加總
	public int getSubTotal() {
		Integer subTotal = 0;
		Set<Integer> set = cart.keySet();
		for (int n : set) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(n);
			OrderItemBean oib = orderMap.keySet().iterator().next();
			Integer price = oib.getUnitPrice();
			Integer quantity = oib.getQuantity();
			subTotal += price * quantity;
		}
		return subTotal;
	}
<<<<<<< HEAD
	
	public int addProductId(int productId) {
		return productId;
	}
	
	
=======

>>>>>>> 7a4065b70d0f7738c36cb5c4c6c705564032ba5f
}

