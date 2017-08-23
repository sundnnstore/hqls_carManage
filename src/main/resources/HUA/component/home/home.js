(function($, doc) {
	$.init();

	$.plusReady(function() {
		var gallery = mui('.mui-slider');
		gallery.slider({
			interval: 3000 //自动轮播周期，若为0则不自动播放，默认为0；
		});
		var backButtonPress = 0;
		$.back = function(event) {
			backButtonPress++;
			if(backButtonPress > 1) {
				plus.runtime.quit();
			} else {
				plus.nativeUI.toast('再按一次退出应用');
			}
			setTimeout(function() {
				backButtonPress = 0;
			}, 1000);
			return false;
		};
		//新增跳转代码 start
		var serviceOrder = doc.getElementById('serviceOrder');
		var store = doc.getElementById('store');
		var purchase = doc.getElementById('purchase');
		var purchaseOrder = doc.getElementById('purchase_order');
		var finance = doc.getElementById('finance');
		//跳转到服务订单 start
		serviceOrder.addEventListener('tap', function(event) {
			console.log('跳转到服务订单页面');
			$.openWindow({
				url: '../Service_order/service_order.html',
				id: 'serviceOrder',
				show: {
					aniShow: 'pop-in'
				},
			});
		});
		//跳转到服务订单 end
		
		//跳转到门店页面 start
		store.addEventListener('tap', function(event) {
			console.log('跳转到门店页面');
			$.openWindow({
				url: '../store/store.html',
				id: 'store',
				show: {
					aniShow: 'pop-in'
				},
			});
		});
		//跳转到门店页面 end
		
		//跳转到采购页面 start
		purchase.addEventListener('tap', function(event) {
			console.log('跳转到采购页面');
			$.openWindow({
				url: '../purchase/purchase.html',
				id: 'purchase',
				show: {
					aniShow: 'pop-in'
				},
			});
		});
		//跳转到采购页面 end
		
		//跳转到采购订单页面 start
		purchaseOrder.addEventListener('tap', function(event) {
			console.log('跳转到采购订单页面');
			$.openWindow({
				url: '../purchase_order/purchase_order.html',
				id: 'purchase_order',
				show: {
					aniShow: 'pop-in'
				},
			});
		});
		//跳转到采购订单页面 end
		
		//跳转到财务页面 start
		finance.addEventListener('tap', function(event) {
			console.log('跳转到财务页面');
			$.openWindow({
				url: '../finance/finance.html',
				id: 'finance',
				show: {
					aniShow: 'pop-in'
				},
			});
		});
		//跳转到财务页面 end
		
		//新增跳转代码 end
	});
}(mui, document));