    var input_field = "";
       var current_file_url = "";
       $("#play_sound").hide();

        function playVoiceRecording(){
            console.log("PRESSED");
            console.log(current_file_url);
        }

        function getVoiceByWord(){
            console.log("input field search : " + input_field);
             $.ajax({
                url: "/sound/"+input_field,
                type: "GET",
                contentType: false,
                cache: false,
                processData: false,
                success: function(response){
                    console.log(response);
                    console.log("response type : " + typeof response);
                    if($.type(response) === "string"){
                        console.log("NO such word");
                        $("#play_sound").hide();
                        $("#search_result").text("There is no result to that word, sorry!");

                    }
                    else{
                        $("#search_result").text(response.word);
                        current_file_url = response.url;
                        console.log(response.word);
                        console.log(response.url);
                        $("#play_sound").show();

                    }
                },error: function(){
                    console.log("ERROR with displaying");
                }
            });
        }

        function getsuggestions() {
            $.ajax({
                url: "/search/"+input_field,
                type: "GET",
                contentType: false,
                cache: false,
                processData: false,
                success: function(response){
                    console.log(response);
                    console.log(response.length);

                     $("#browsers").empty();

                     for (var i=0; i < response.length; i++) {
                        console.log(response[i].word);
                        $('#browsers').append("<option value='" + response[i].word + "'>");
                    }

                },error: function(){
                    console.log("ERROR with searching");
                }
            });
        }

        function controlIfChanged(){

            if(input_field != $('#searchKeyword').val()){
                input_field = $('#searchKeyword').val();
                if(input_field == ""){
                    $("#browsers").empty();
                }
                else{
                    getsuggestions();
                }
                console.log("UUS väärtus" + input_field);
            }
            setTimeout(controlIfChanged, 500);
        }

        setTimeout(controlIfChanged, 500);
