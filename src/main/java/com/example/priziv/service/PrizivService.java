package com.example.priziv.service;

import com.example.priziv.dto.PrizivDto;
import com.example.priziv.repository.PrizivRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrizivService {
    private final PrizivRepository prizivRepository;

    public PrizivService(PrizivRepository prizivRepository) {
        this.prizivRepository = prizivRepository;
    }

    public void getAllPriziv(AuthUser authUser, Model model) {

        List<PrizivDto> prizivList = prizivRepository.findAll(Sort.by("dateArrival", "dateDeparture"))
                .stream()
                .map(PrizivDto::getInstance)
                .collect(Collectors.toList());

        int totalPeopleAmount = 0;
        int totalIssued = 0;
        int totalNotIssued = 0;

        for (PrizivDto prizivDto : prizivList) {
            totalPeopleAmount += prizivDto.getPeopleAmmount();
            totalIssued += prizivDto.getIssued();
            totalNotIssued += prizivDto.getIllList().size();
        }


        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("prizivList", prizivList);
        model.addAttribute("totalPeopleAmount", totalPeopleAmount);
        model.addAttribute("totalIssued", totalIssued);
        model.addAttribute("totalNotIssued", totalNotIssued);
    }
}
