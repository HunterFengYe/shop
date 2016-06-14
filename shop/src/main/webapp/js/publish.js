    function getObjectURL(file) {
          var url = null ; 
          if (window.createObjectURL!=undefined) { // basic
              url = window.createObjectURL(file) ;
          } else if (window.URL!=undefined) { // mozilla(firefox)
              url = window.URL.createObjectURL(file) ;
          } else if (window.webkitURL!=undefined) { // webkit or chrome
              url = window.webkitURL.createObjectURL(file) ;
          }
          return url ;
        }
       
       
        $('#select-photo').change(function () {
         var fileNums=this.files.length;
         var fileArr=this.files; 
     if (fileNums > 0) {
        for(var i=0;i<fileNums;i++){
        var file=fileArr[0];
        var url=getObjectURL(fileArr[i]);
        var img=$('<img src=\''+url+'\' class=\'img\' style="width:80px;height:80px;">');
        item = $('<li class=\'li-item\'><span class=\'img-close\'></span></li>').append(img);
        $('.photo-list').append(item);
      }
     }
        });