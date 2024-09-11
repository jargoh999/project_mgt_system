package com.jargoh.project_management_system.services.impl;

import com.jargoh.project_management_system.model.PlanType;
import com.jargoh.project_management_system.model.Subscription;
import com.jargoh.project_management_system.model.User;
import com.jargoh.project_management_system.repository.Subscriptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubscriptionService {


    private final Subscriptions subscriptions;
    public Subscription createSubscription(User user){
         Subscription subscription = new Subscription();
         subscription.setUser(user);
         subscription.setSubscriptionStartDate(LocalDate.now());
         subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
         subscription.setValid(true);
         subscription.setPlanType(PlanType.FREE);
         return subscriptions.save(subscription);
    }

    public Subscription getUsersSubscription(Long userId){
             Subscription subscription= subscriptions.findByUserId(userId);
             if(!isValid(subscription)){
                 subscription.setPlanType(PlanType.FREE);
                 subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
                 subscription.setSubscriptionStartDate(LocalDate.now());
             }
             return subscriptions.save(subscription);
    }

    public Subscription upgradeSubscription(Long userId, PlanType planType){
            Subscription subscription = subscriptions.findByUserId(userId);
            subscription.setPlanType(planType);
            subscription.setSubscriptionStartDate(LocalDate.now());
            if(planType.equals(PlanType.ANNUALLY)){
                subscription.setSubscriptionEndDate(LocalDate.now().plusYears(1));
            }else{
                subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
            }
            return subscriptions.save(subscription);
    }

    public boolean isValid(Subscription subscription){
          if(subscription.getPlanType().equals(PlanType.FREE)){
              return true;
          }
          LocalDate endDate = subscription.getSubscriptionEndDate();
          LocalDate currentDate = LocalDate.now();
          return endDate.isAfter(currentDate)||endDate.isEqual(currentDate);
    }


}
