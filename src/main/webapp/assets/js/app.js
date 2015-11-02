(function($) {
  'use strict';
  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
      $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
    });
  });
})(jQuery);



function commitTask(){
	
	
}



function getLog(){
	
var stop = "0";
var lastTimeFileSize =0;

var updater = {
			poll : function() {
				$.ajax({
					url : "commitTask.do",
					type : "POST",
					data: {"lastTimeFileSize": lastTimeFileSize},
					dataType : "json",
					success : updater.onSuccess,
					error : updater.onError
				});
			},
			check: function (){
				console.log("stop",stop);
				if(stop == "0"){
				    updater.poll();
				}
			},
			onSuccess : function(data, dataStatus) {
				try {
				console.log("data stop",data.stop);
				console.log("data lastTimeFileSize",data.lastTimeFileSize);
				stop = data.stop;
				lastTimeFileSize =data.lastTimeFileSize;
				
				$("#log").append(data.data);
				} catch (e) {
					updater.onError();
					return;
				}
				interval = setTimeout(updater.check, 10);
			},
			onError : function() {
				console.log("获取日志失败");
			}
		};
	
    updater.poll();
}































