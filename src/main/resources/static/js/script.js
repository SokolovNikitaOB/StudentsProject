$(function(){
    const appendStudent = function(data){
        let student = '<div class="student-name-in-list"><a href="#" data-id="' + data.id + '">' + data.studentName + '</a><img src="img/grave.jpg"></div>'
        $('.student-list').prepend('<div class="student">' + student + '</div>');
    };

    //get all students
    /*$.get('/students/', function(response){
        for(prop in response){
            appendStudent(response[prop]);
        }
    })*/

    //get student
    $(document).on('click', '.student-name-in-list a', function(){
        let link = $(this);
        let studentId = link.data('id');
        let linkContainer = link.parent().parent();
        console.log(studentId);
        $.ajax({
                    method: "GET",
                    url: '/students/' + studentId,
                    success: function(response){
                            let code = '<div class="student-information-in-list">' +
                            response.studentBirthdate + '<br>' +
                            response.studentUniversity + ' - ' +
                            response.studentFaculty + ' - ' +
                            response.studentCourse + ' курс</div>';
                            linkContainer.append(code);
                    },
                    error: function(response){
                        if(response.status == 404){
                            alert('Студент не найден');
                        }
                    }
        });
        return false;
    });

    //add student
    $("#submit").on('click', function(event){
        $.ajax({
            method: "POST",
            url: '/students/',
            data: $('.student-form form').serialize(),
            success: function(response){
                let student = {};
                student.id = response;
                let data = $('.student-form form').serializeArray();
                for(let i = 0; i < data.length; i++){
                    student[data[i]['name']] = data[i]['value'];
                }
                appendStudent(student);
                $('.student-form form')[0].reset();
            }
        });
        return false;
    });

    //delete all
    $(document).on('click','#delete-all-students', function(){
        let link = $(this).parent();
        $.ajax({
               method: "DELETE",
               url: '/students/',
               success: function(){
                   link.empty();
                   link.append('<button id="delete-all-students">Очистить список</button>');
               }
        });
    });

    //delete
    $(document).on('click', '.student-name-in-list img', function(){
        let link = $(this).parent();
        let studentId = link.children('a').data('id');
        $.ajax({
            method: "DELETE",
            url: '/students/' + studentId,
            success: function(response){
                link.parent().remove();
            }
        });
    });



});