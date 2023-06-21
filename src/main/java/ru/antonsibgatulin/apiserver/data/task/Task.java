package ru.antonsibgatulin.apiserver.data.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import ru.antonsibgatulin.apiserver.data.respond.Respond;
import ru.antonsibgatulin.apiserver.data.respond.repository.RespondRepository;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.utils.EnumListConverter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(150)")
    private String name;
    @Column(columnDefinition = "VARCHAR(4096)")
    private String description;
    private Integer price;
    private Integer betterPrice;

    @ManyToMany
    @JoinTable
    @JsonIgnore
    private List<Respond> responds = new ArrayList<>();

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Respond> respond;




    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Convert(converter = EnumListConverter.class)
    private List<TypeTask> typeTasks = new ArrayList<>();

    private Long timeCreate;
    private Long countLike;
    private Long countView;

    public void clear(RespondRepository respondRepository) {
        respondRepository.deleteAll(responds);
        this.responds.clear();
    }
    public void prepared(){
        this.respond = responds;
    }

    public boolean checkResponse(User user) {
        for(var respone:this.responds){
            if(respone.getUser().getId() == user.getId()){
                return true;
            }
        }
        return false;
    }
}
