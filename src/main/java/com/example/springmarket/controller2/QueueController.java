package com.example.springmarket.controller2;

import com.example.springmarket.model.Person;
import com.example.springmarket.model.Queue;
import com.example.springmarket.repository.PersonRepository;
import com.example.springmarket.repository.QueueRepository;
import com.example.springmarket.service.PersonService;
import com.example.springmarket.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.TreeMap;

@Controller
public class QueueController {

    @Autowired
    private PersonService personService;


    @Autowired
    private QueueService queueService;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/queue/list/1")
    public String getAllQueueForView(Model model){
        List<Queue> queues = queueService.getAll();
        model.addAttribute("queues", queues);
        return "showAllQueues";
    }



    @GetMapping("/queue/{id}")
    public String getPersonFromQueue(@PathVariable("id") int queueId, Model model, HttpServletRequest httpServletRequest){

        List<Person> list;
        String name =  httpServletRequest.getParameter("name");
        String surname = httpServletRequest.getParameter("surname");
        Queue queue = queueService.getById(queueId);
        if(name != null && surname != null){
            if(personRepository.findPersonByNameAndSurname(name, surname) != null){
                TreeMap<Integer, Person> map = queue.createNumberList();
                list = personRepository.findPersonByNameAndSurname(name, surname);
                Person person = list.get(0);
                Integer number = 0;
                for (Integer k : map.keySet())
                {
                    if (map.get(k).equals(person) )  {
                        number = k;
                        break;
                    }
                }

                String mess = "Твій номер в черзі = " + number;
                model.addAttribute("mess", mess);
            }
        }
        List<Person> personList =  queue.getPersonList();
        if(personList != null){
            model.addAttribute("map", queue.createNumberList());
            System.out.println(queue.createNumberList());
            System.out.println(11111111+ "sdsdsdsdsdsdsdsds\nertererere");
        }


        model.addAttribute("personList", personList);
        model.addAttribute("queue", queue);
        return "showPerson";
    }

    @PostMapping("/queue/{id}")
    public String deleteQueue(@PathVariable("id") int queueId){
        queueService.deleteById(queueId);
//        String str = String.format("redirect:/queue/{%d}", queueId);
        return "redirect:/queue/list/1";
    }



    @PostMapping("/queue/person")
    public String getPerson(@RequestParam("name") String name, @RequestParam("surname") String surname, Model model){
        List<Person> list;
        if(personRepository.findPersonByNameAndSurname(name, surname) != null){
            list = personRepository.findPersonByNameAndSurname(name, surname);
            Person person = list.get(0);
            String mess = "Твій номер в черзі = " + person.getId();
            model.addAttribute("mess", mess);
        }
        return "showPersonNumber";
    }

    @GetMapping("/queue/list/2")
    public String getAllQueueForEnroll(Model model){
        List<Queue> queues = queueService.getAll();
        model.addAttribute("queues", queues);
        return "showAllQueuesForEnroll";
    }



    @GetMapping("/person/add")
    public String addPersonToQueue(){
        return "addPerson";
    }


    @PostMapping("/person/add")
    public String addPersonToQueue(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                     @RequestParam("mail") String String,
                                     @RequestParam("queueName") String queueName, Model model) throws Exception {
        Queue queue = queueRepository.findByName(queueName);
        Person person = new Person(name, surname);
        if (queue != null){
            queue.addPerson(person);
            personService.save(person);
        }

        else
            throw new Exception("wrong name of queue");
        model.addAttribute("name", queueName);
        return "redirect:/";
    }


    @GetMapping("/queue/add")
    public String addQueue(){
        return "addQueue";
    }

    @PostMapping("/queue/add")
    public String addQueue(@RequestParam("name") String name, @RequestParam("imgUrl") String imgUrl, @RequestParam("description") String description)  {
        Queue queue = new Queue(name, imgUrl, description);
        queueService.save(queue);
        return "redirect:/";
    }
}
