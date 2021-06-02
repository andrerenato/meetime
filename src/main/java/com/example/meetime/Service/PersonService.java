package com.example.meetime.Service;

import com.example.meetime.Domain.Lead;
import com.example.meetime.Domain.Person;
import com.example.meetime.Domain.Request.PersonRequest;
import com.example.meetime.Domain.Response.PipedriveResponse;
import com.example.meetime.Utils.PipedriveRestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    @Autowired
    private PipedriveRestConnection pipedriveRestConnection;

    private List<Person> personsOnMemory = new ArrayList<>();

    public Person create(Lead lead) {
        Optional<Person> person = personsOnMemory.stream().filter(p -> p.getEmail().contains(lead.getEmail())).findFirst(); //alterar
        if (person.isPresent()) {
            return person.get();
        }

        PersonRequest request = new PersonRequest();
        request.setName(lead.getName());
        request.setEmail(Collections.singletonList(lead.getEmail()));

        PipedriveResponse<Person> post = pipedriveRestConnection.post("/persons", request, Person.class);
        Person createdPerson = post.getData();
        personsOnMemory.add(createdPerson);
        return createdPerson;
    }
}
