requirejs.config({

	baseUrl: config.contextPath + "js",
    shim : {
        "bootstrap" : { "deps" :['jquery'] },
        "loading"  	: { "deps" :['jquery'] }
    },
    paths: {
        "jquery" 	: "lib/jquery-3.1.1.min",
        "bootstrap" :  "lib/bootstrap.min",
        "loading" 	: "lib/jquery.loading.min"
    }
    
});

requirejs(["bootstrap", 'loading'], function(){
	
	$('base').attr('href',config.contextPath);
	
	$.ajaxSetup({
		
		 beforeSend: function(xhr){
		    $( this.target ).data('xhr', $(this));
			if ( this.target ){
				$( this.target ).loading({
					message: 'Caricamento in corso...'
				});
			}else{
				$( 'body' ).loading();
			}
		 },
		 complete : function(event, xhr, settings ){
			
			 
			if ( this.target ){
				$( this.target ).loading('stop');
			}else{
				$( 'body' ).loading('stop');
			}
			if ( xhr === 'success'){
				
				if ( this.target ){
					
					$(this.target).html(event.responseText);
					
				}
				
				var params = this.params;			 
				if ( this.module != null ){
					 
					requirejs([this.module], function(mod){
						mod.onLoad(params);
					});
					
				} 
				if ( this.onAfterSuccess ){
					this.onAfterSuccess(event, xhr, settings);
				}
			}

		 },
		 error : function(xhr,status,error){			 
		 
			 $( this.target ).html(xhr.responseText);
			 if (config.devmode){ 
				 $( this.target ).find('.send-data').click(function(event){
					 var xhr = $(event.target).closest('.error-data').parent().data('xhr')[0];
					 $.ajax(xhr);				 
				 });
			 }
			 if ( this.onAfterSuccess ){
				this.onAfterError(xhr,status,error);
			 }
		 } 
		 
	}); 
	
	require(['modules/index'],function(m){ 
		m.onLoad();
	});
	
	
});